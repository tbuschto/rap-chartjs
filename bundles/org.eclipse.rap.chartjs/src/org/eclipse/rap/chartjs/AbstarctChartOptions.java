/*******************************************************************************
 * Copyright (c) 2014 EclipseSource and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: EclipseSource - initial API and implementation
 ******************************************************************************/

package org.eclipse.rap.chartjs;

import org.eclipse.rap.json.JsonObject;

public class AbstarctChartOptions
{

    private boolean animation    = true;
    private boolean showToolTips = true;

    private Legend  legend       = new Legend();

    /**
     * Sets whether to play an "appear" animation for a new chart drawing.
     * 
     * @param animation
     * @return
     */
    public AbstarctChartOptions setAnimation(boolean animation)
    {
        this.animation = animation;
        return this;
    }

    public boolean getAnimation()
    {
        return this.animation;
    }

    public Legend getLegend()
    {
        return legend;
    }

    /**
     * Sets whether to show tooltips on data segments/points/bars.
     *
     * @param showToolTips
     * @return
     */
    public AbstarctChartOptions setShowToolTips(boolean showToolTips)
    {
        this.showToolTips = showToolTips;
        return this;
    }

    public boolean getShowToolTips()
    {
        return this.showToolTips;
    }

    public JsonObject toJson()
    {
        JsonObject result = new JsonObject();
        result.add("animation", new Animation(animation).toJson());
        // result.add( "animation", animation );
        result.add("legend", legend.toJson());

        Tooltips tooltips = new Tooltips();
        tooltips.enabled = showToolTips;
        result.add("tooltips", tooltips.toJson());

        return result;
    }

    public static class Animation
    {
        public Animation(boolean animation)
        {
            if (!animation)
            {
                duration = 0;
            }
        }

        private int    duration = 1000;
        private String easing   = "easeOutQuart";

        JsonObject toJson()
        {
            JsonObject result = new JsonObject();
            result.add("duration", duration).add("easing", easing);

            return result;
        }

    }

    public static class Tooltips
    {
        boolean enabled = true;

        public Tooltips()
        {

        }

        JsonObject toJson()
        {
            JsonObject result = new JsonObject();
            result.add("enabled", enabled);

            return result;
        }

    }

    public static class Legend
    {
        boolean enabled  = true;
        String  position = "bottom";

        public Legend()
        {

        }

        JsonObject toJson()
        {
            JsonObject result = new JsonObject();
            result.add("display", enabled);
            if (enabled)
            {
                result.add("position", position);
            }

            return result;
        }

        public void setEnabled(boolean enabled)
        {
            this.enabled = enabled;
        }

        public boolean isEnabled()
        {
            return enabled;
        }

        public void setPosition(String position)
        {
            this.position = position;
        }

        public String getPosition()
        {
            return position;
        }

    }

}
