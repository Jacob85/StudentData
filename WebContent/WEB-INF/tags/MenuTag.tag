<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="menuItem1" required="true"%>
<%@ attribute name="menuItem2" required="true"%>
<%@ attribute name="menuItem3" required="true"%>
<%@ attribute name="menuItem1Link" required="true"%>
<%@ attribute name="menuItem2Link" required="true"%>
<%@ attribute name="menuItem3Link" required="true"%>
<%

String itme1Opacity=".6",itme2Opacity=".6",itme3Opacity=".6";
if(menuItem1Link.trim().equals("#"))
{
	itme1Opacity=".9";
}
else if(menuItem2Link.trim().equals("#"))
{
	itme2Opacity=".9";
}
else if(menuItem3Link.trim().equals("#"))
{
	itme3Opacity=".9";
}
out.print("<a id=\"menuitem\" href=\""+menuItem1Link+"\" style=\" left: 30%;opacity:"+itme1Opacity+"\">"+menuItem1+"</a>"
+"<a id=\"menuitem\" href=\""+menuItem2Link+"\" style=\" left: 50%;opacity:"+itme2Opacity+"\">"+menuItem2+"</a>"+
"<a id=\"menuitem\" href=\""+menuItem3Link+"\" style=\"left: 70%;opacity:"+itme3Opacity+"\">"+menuItem3+"</a>"
    +"<p class=\"tabBar\"></p>");
%>

