/*******************************************************************************
 * Copyright (c) 2014 EclipseSource and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: EclipseSource - initial API and implementation
 ******************************************************************************/

package org.eclipse.rap.chartjs;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.chartjs.internal.ChartPaintListener;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.internal.lifecycle.WidgetUtil;
import org.eclipse.rap.rwt.scripting.ClientListener;
import org.eclipse.rap.rwt.service.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("deprecation")
public abstract class AbstractChart extends Canvas
{

    private static final String CHART_OPTIONS = "chartOptions";
    private static final String CHART_DATA    = "chartData";
    private static final String CHART_MIN_JS  = "Chart.js";
    private static final String CHART_TYPE    = "chartType";

    public AbstractChart(Composite parent, int style)
    {
        super(parent, style);
        registerJS();
        requireJS();
        // NOTE: RAP re-transfers all attached widget data, even if only one of
        // them changes,
        // but JsonObject/JsonArray aren't deep-compared
        WidgetUtil.registerDataKeys(CHART_TYPE, CHART_DATA, CHART_OPTIONS);
        addPaintListener();
        applyFixes();
    }

    public void clear()
    {
        setData(CHART_TYPE, JsonObject.NULL); // "null" won't be synchronized
        redraw();
    }

    protected void drawChart(String type, JsonObject options, JsonValue data)
    {
        setData(CHART_TYPE, type);
        setData(CHART_OPTIONS, options);
        setData(CHART_DATA, data);
        redraw();
    }

    private void addPaintListener()
    {
        addListener(SWT.Paint, ChartPaintListener.getInstance());
    }

    private void applyFixes()
    {
        // See RAP Bug 391414
        ClientListener listener = new ClientListener("function handleEvent( event ) {\n" + "  event.widget.redraw();" + "}\n");
        addListener(SWT.Show, listener);
    }

    public static void requireJS()
    {
        JavaScriptLoader service = RWT.getClient().getService(JavaScriptLoader.class);
        service.require(RWT.getResourceManager().getLocation(CHART_MIN_JS));
    }

    public static void registerJS()
    {
        ResourceManager manager = RWT.getResourceManager();
        if (!manager.isRegistered(CHART_MIN_JS))
        {
            InputStream inputStream = ChartPaintListener.class.getResourceAsStream(CHART_MIN_JS);
            manager.register(CHART_MIN_JS, inputStream);
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        }
    }

}
