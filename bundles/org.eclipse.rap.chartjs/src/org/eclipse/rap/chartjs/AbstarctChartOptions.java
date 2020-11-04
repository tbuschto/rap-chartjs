/*******************************************************************************
 * Copyright (c) 2014 EclipseSource and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: EclipseSource - initial API and implementation
 ******************************************************************************/

package org.eclipse.rap.chartjs;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;

public class AbstarctChartOptions
{

    private boolean animation    = true;
    private boolean showToolTips = true;

    private Legend  legend       = new Legend();
    private Plugins plugins      = new Plugins();

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

    public Plugins getPlugins()
    {
        return plugins;
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
        result.add("plugins", plugins.toJson());

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
            
            {
                JsonObject callbacks = new JsonObject();
                JsonObject label = new JsonObject();
                callbacks.add("label", label);
                result.add("callbacks", callbacks);
                }
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

    public static class Plugins
    {
        LabelsPlugin labels = new LabelsPlugin();

        public Plugins()
        {

        }

        JsonObject toJson()
        {
            JsonObject result = new JsonObject();
            result.add("labels", labels.toJson());

            return result;
        }

        public LabelsPlugin getLabels()
        {
            return labels;
        }

    }

    public static class LabelsPlugin
    {
        // render 'label', 'value', 'percentage', 'image' or custom function,
        // default is 'percentage'

        private String   render                = "value";

        // precision for percentage, default is 0
        private int     precision             = 0;

        // identifies whether or not labels of value 0 are displayed, default is
        // false
        private boolean showZero              = true;

        // font size, default is defaultFontSize
        private int     fontSize              = 12;

        // font color, can be color array for each data or function for dynamic
        // color, default is defaultFontColor
        private String  fontColor             = "#fff";

        // font style, default is defaultFontStyle
        private String  fontStyle             = "normal";

        // font family, default is defaultFontFamily
        private String  fontFamily            = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

        // draw text shadows under labels, default is false
        private boolean textShadow            = false;

        // text shadow intensity, default is 6
        private int     shadowBlur            = 6;

        // text shadow X offset, default is 3
        private int     shadowOffsetX         = 3;

        // text shadow Y offset, default is 3
        private int     shadowOffsetY         = 3;

        // text shadow color, default is 'rgba(0,0,0,0.3)'
        private String  shadowColor           = "rgba(0,0,0,0.3)";

        // draw label in arc, default is false
        // bar chart ignores this
        private boolean arc                   = false;

        // position to draw label, available value is 'default', 'border' and
        // 'outside'
        // bar chart ignores this
        // default is 'default'
        private String  position              = "default";

        // draw label even it's overlap, default is true
        // bar chart ignores this
        private boolean overlap               = true;

        // show the real calculated percentages from the values and don't apply
        // the additional logic to fit the percentages to 100 in total, default
        // is false
        private boolean showActualPercentages = false;

        // set images when `render` is 'image'
        /*
         * images: [ { src: 'image.png', width: 16, height: 16 } ],
         */

        // add padding when position is `outside`
        // default is 2
        private int     outsidePadding        = 2;

        // add margin of text when position is `outside` or `border`
        // default is 2
        private int     textMargin            = 2;

        public LabelsPlugin()
        {

        }

      

        JsonObject toJson()
        {
            JsonObject result = new JsonObject();
            result.add("render", render);
            result.add("precision", precision);
            result.add("showZero", showZero);
            result.add("fontSize", fontSize);
            result.add("fontColor", fontColor);
            result.add("fontStyle", fontStyle);
            result.add("fontFamily", fontFamily);
            result.add("textShadow", textShadow);
            result.add("shadowBlur", shadowBlur);
            result.add("shadowOffsetX", shadowOffsetX);
            result.add("shadowOffsetY", shadowOffsetY);
            result.add("shadowColor", shadowColor);
            result.add("arc", arc);
            result.add("position", position);
            result.add("overlap", overlap);
            result.add("showActualPercentages", showActualPercentages);
            result.add("outsidePadding", outsidePadding);
            result.add("textMargin", textMargin);

            return result;
        }
        
        JsonArray toJsArray(String [] data) {
            JsonArray array = new JsonArray();
            
            for (Object object : data)
            {
                if(object instanceof String)
                    array.add((String)object);
                if(object instanceof Integer)
                    array.add((Integer)object);
                if(object instanceof Boolean)
                    array.add((Boolean)object);
            }
            
            return array;
        }

        public int getPrecision()
        {
            return precision;
        }

        public void setPrecision(int precision)
        {
            this.precision = precision;
        }

        public boolean isShowZero()
        {
            return showZero;
        }

        public void setShowZero(boolean showZero)
        {
            this.showZero = showZero;
        }

        public int getFontSize()
        {
            return fontSize;
        }

        public void setFontSize(int fontSize)
        {
            this.fontSize = fontSize;
        }

        public String getFontColor()
        {
            return fontColor;
        }

        public void setFontColor(String fontColor)
        {
            this.fontColor = fontColor;
        }

        public String getFontStyle()
        {
            return fontStyle;
        }
        
        

        public String getRender()
        {
            return render;
        }



        public void setRender(String render)
        {
            this.render = render;
        }



        public void setFontStyle(String fontStyle)
        {
            this.fontStyle = fontStyle;
        }

        public String getFontFamily()
        {
            return fontFamily;
        }

        public void setFontFamily(String fontFamily)
        {
            this.fontFamily = fontFamily;
        }

        public boolean isTextShadow()
        {
            return textShadow;
        }

        public void setTextShadow(boolean textShadow)
        {
            this.textShadow = textShadow;
        }

        public int getShadowBlur()
        {
            return shadowBlur;
        }

        public void setShadowBlur(int shadowBlur)
        {
            this.shadowBlur = shadowBlur;
        }

        public int getShadowOffsetX()
        {
            return shadowOffsetX;
        }

        public void setShadowOffsetX(int shadowOffsetX)
        {
            this.shadowOffsetX = shadowOffsetX;
        }

        public int getShadowOffsetY()
        {
            return shadowOffsetY;
        }

        public void setShadowOffsetY(int shadowOffsetY)
        {
            this.shadowOffsetY = shadowOffsetY;
        }

        public String getShadowColor()
        {
            return shadowColor;
        }

        public void setShadowColor(String shadowColor)
        {
            this.shadowColor = shadowColor;
        }

        public boolean isArc()
        {
            return arc;
        }

        public void setArc(boolean arc)
        {
            this.arc = arc;
        }

        public String getPosition()
        {
            return position;
        }

        public void setPosition(String position)
        {
            this.position = position;
        }

        public boolean isOverlap()
        {
            return overlap;
        }

        public void setOverlap(boolean overlap)
        {
            this.overlap = overlap;
        }

        public boolean isShowActualPercentages()
        {
            return showActualPercentages;
        }

        public void setShowActualPercentages(boolean showActualPercentages)
        {
            this.showActualPercentages = showActualPercentages;
        }

        public int getOutsidePadding()
        {
            return outsidePadding;
        }

        public void setOutsidePadding(int outsidePadding)
        {
            this.outsidePadding = outsidePadding;
        }

        public int getTextMargin()
        {
            return textMargin;
        }

        public void setTextMargin(int textMargin)
        {
            this.textMargin = textMargin;
        }
        
        
        

    }

}
