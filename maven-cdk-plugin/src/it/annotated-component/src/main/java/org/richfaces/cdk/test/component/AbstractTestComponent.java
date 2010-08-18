/*
 * $Id$
 *
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.richfaces.cdk.test.component;

import java.util.List;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.component.ValueHolder;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;

import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.Description;
import org.richfaces.cdk.annotations.Facet;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.RendererSpecificComponent;
import org.richfaces.cdk.annotations.Signature;
import org.richfaces.cdk.annotations.Tag;
import org.richfaces.cdk.test.event.TestEvent;
import org.richfaces.cdk.test.model.TestModel;

/**
 * <p class="changed_added_4_0">
 * Test component that generates a set of the output components.
 * </p>
 * 
 * @author asmirnov@exadel.com
 * 
 */
@JsfComponent(type = "org.richfaces.cdk.test.TestComponent",
    family="org.richfaces.Test",
    description=@Description(displayName="Test Component",largeIcon="large.gif",smallIcon="spall.png"),
    generate="org.richfaces.cdk.test.component.UITestComponent",
    facets=@Facet(name="caption",generate=true,description=@Description("Caption Facet")),
    fires=TestEvent.class,
    interfaces=ValueHolder.class,
    components={
        @RendererSpecificComponent(type = "org.richfaces.cdk.test.TestHtmlAbbr",
            description=@Description(displayName="Test HTML5 abbreviation",largeIcon="large.gif",smallIcon="spall.png"),
            tag=@Tag(name="abbr",generate=true,handler="org.richfaces.cdk.test.facelets.AbbrTagHandler"),
            generate="org.richfaces.cdk.test.component.html.HtmlTestAbbr",
            interfaces=Html5Attributes.class,
            renderer=@JsfRenderer(type="org.richfaces.cdk.test.HtmlAbbrRenderer")),
        @RendererSpecificComponent(type = "org.richfaces.cdk.test.TestHtmlDfn",
            tag=@Tag(name="dfn"),
            generate="org.richfaces.cdk.test.component.html.HtmlTestDfn",
            attributes="html5.xml")
    }
)
public abstract class AbstractTestComponent extends UIComponentBase /*implements ValueHolder */{
    
    @Attribute
    private int length;

    @Attribute
    public abstract String getTitle();
    
    /**
     * Test Attribute
     */
    @Attribute
    public abstract List<String> getTestValue();

    /**
     * Bar Attribute
     */
    @Attribute
    public abstract void setBarValue(List<Object> bar);
    
    @Attribute(signature = @Signature(parameters = String.class, returnType = Boolean.class)) 
    public abstract MethodExpression getMethodExpressionListener(); 

    @Attribute(signature = @Signature(parameters = {String.class,Integer.class}, returnType = int.class)) 
    public abstract MethodBinding getMethodBindingListener(); 

    @Attribute(required=true,signature=@Signature(returnType=TestModel.class)) 
    public abstract ValueExpression getValueExpressionProperty();
    
    @Attribute(required=true,signature=@Signature(returnType=List.class,parameters={String.class,int.class})) 
    public abstract ValueBinding getValueBindingProperty(); 
}