/*******************************************************************************
 * Copyright (c) 2014 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/

package org.eclipse.rap.chartjs;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rap.chartjs.internal.ChartPaintListener;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.lifecycle.WidgetUtil;
import org.eclipse.rap.rwt.scripting.ClientListener;
import org.eclipse.rap.rwt.service.ResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("deprecation")
public class Chart extends Canvas {

	private static final String CHART_OPTIONS = "chartOptions";
	private static final String CHART_DATA = "chartData";
	private static final String CHART_TYPE = "chartType";
	private static final String CHART_MIN_JS = "Chart.js";

	private static final String LINE_CHART = "Line";
	private static final String PIE_CHART = "Pie";
	private static final String BAR_CHART = "Bar";
	private static final String STACKED_BAR_CHART = "StackedBar";
	private static final String RADAR_CHART = "Radar";
	private static final String POLAR_AREA_CHART = "PolarArea";
	private static final String DOUGHNUT_CHART = "Doughnut";

	public Chart(Composite parent, int style) {
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

	public void drawLineChart(ChartRowData data) {
		drawLineChart(data, null);
	}

	public void drawLineChart(ChartRowData data, ChartOptions options) {
		drawChart(LINE_CHART, getLineChartOptions(options), data.toJson());
	}

	public void drawBarChart(ChartRowData data) {
		drawBarChart(data, null);
	}

	public void drawBarChart(ChartRowData data, ChartOptions options) {
		drawChart(BAR_CHART, getBarChartOptions(options), data.toJson());
	}
	
	public void drawStackedBarChart(ChartStackedBarData data) {
		drawStackedBarChart(data, null);
	}
	
	public void drawStackedBarChart(ChartStackedBarData data, ChartOptions options) {
		drawChart(STACKED_BAR_CHART, getStackedBarChartOptions(options), data.toJson());
	}

	public void drawRadarChart(ChartRowData data) {
		drawRadarChart(data, null);
	}

	public void drawRadarChart(ChartRowData data, ChartOptions options) {
		drawChart(RADAR_CHART, getLineChartOptions(options), data.toJson());
	}

	public void drawPolarAreaChart(ChartPointData data) {
		drawPolarAreaChart(data, null);
	}

	public void drawPolarAreaChart(ChartPointData data, ChartOptions options) {
		drawChart(POLAR_AREA_CHART, getSegmentChartOptions(options), data.toJson());
	}

	public void drawPieChart(ChartPointData data) {
		drawPieChart(data, null);
	}

	public void drawPieChart(ChartPointData data, ChartOptions options) {
		drawChart(PIE_CHART, getSegmentChartOptions(options), data.toJson());
	}

	public void drawDoughnutChart(ChartPointData data) {
		drawDoughnutChart(data, null);
	}

	public void drawDoughnutChart(ChartPointData data, ChartOptions options) {
		drawChart(DOUGHNUT_CHART, getSegmentChartOptions(options), data.toJson());
	}

	public void clear() {
		setData(CHART_TYPE, JsonObject.NULL); // "null" won't be synchronized
		redraw();
	}

	private void drawChart(String type, JsonObject options, JsonValue data) {
		setData(CHART_TYPE, type);
		setData(CHART_OPTIONS, options);
		setData(CHART_DATA, data);
		redraw();
	}

	private JsonObject getSegmentChartOptions(ChartOptions options) {
		if (options == null) {
			return new JsonObject();
		}
		JsonObject json = options.toJson();
		json.set("segmentShowStroke", json.get("showStroke").asBoolean());
		json.set("segmentStrokeWidth", json.get("strokeWidth").asInt());
		return json;
	}

	private JsonObject getBarChartOptions(ChartOptions options) {
		if (options == null) {
			return new JsonObject();
		}
		JsonObject json = options.toJson();
		json.set("barShowStroke", json.get("showStroke").asBoolean());
		json.set("barStrokeWidth", json.get("strokeWidth").asInt());
		return json;
	}
	
	private JsonObject getStackedBarChartOptions(ChartOptions options) {
		if (options == null) {
			return new JsonObject();
		}
		JsonObject json = options.toJson();
		json.set("barShowStroke", json.get("showStroke").asBoolean());
		json.set("barStrokeWidth", json.get("strokeWidth").asInt());
		return json;
	}

	private JsonObject getLineChartOptions(ChartOptions options) {
		if (options == null) {
			return new JsonObject();
		}
		JsonObject json = options.toJson();
		json.set("datasetStroke", json.get("showStroke").asBoolean());
		json.set("datasetStrokeWidth", json.get("strokeWidth").asInt());
		return json;
	}

	private void addPaintListener() {
		addListener(SWT.Paint, ChartPaintListener.getInstance());
	}

	private void applyFixes() {
		// See RAP Bug 391414
		ClientListener listener = new ClientListener("function handleEvent( event ) {\n" + "  event.widget.redraw();" + "}\n");
		addListener(SWT.Show, listener);
	}

	private void requireJS() {
		JavaScriptLoader service = RWT.getClient().getService(JavaScriptLoader.class);
		service.require(RWT.getResourceManager().getLocation(CHART_MIN_JS));
	}

	private void registerJS() {
		ResourceManager manager = RWT.getResourceManager();
		if (!manager.isRegistered(CHART_MIN_JS)) {
			InputStream inputStream = ChartPaintListener.class.getResourceAsStream(CHART_MIN_JS);
			manager.register(CHART_MIN_JS, inputStream);
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
