<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    <title>Project Management System</title>

  
<body>

<div class="bg">

    <div id="header" class="">

        <div class="container narrow">

            <div id="logo" class="">
            
                            
                    <a href="https://oopms.googlcode.com/"><img src="./css/logo.png" alt="Project Bubble" id="fDI2MDc" class="pic"></a>
                    
                                
            </div>
            <%-- Define actionURL --%>
<portlet:actionURL var="formAction">
  <portlet:param name="action" value="forward" />
</portlet:actionURL>
      
            <div id="toolbar" class="">
            
                                    
                    <div id="login">
        
                      <a href='<portlet:renderURL><portlet:param name="action" value="forward"/>
           </portlet:renderURL>'>Render Forward</a>
  <a href='<portlet:actionURL><portlet:param name="action" value="forward"/>
           </portlet:actionURL>'>Action Forward</a>
                    </div>
    
                    <div class="callus">
                    <form:form method="post" commandName="loginForm" action="${formAction2}">
   <form:button id="changepw"  class="button blue small" name="Change Password" value="Change Password"/>
 </form:form>
                        <span>Contact us: <strong>oopms@googlegroup.com</strong></span>
                        <span><a href="https://oopms.googlcode.com/help">Help</a></span>
                        <span><img src="./css/padlock.png" alt="Login"> <a href="./css/Project Bubble.htm">Login</a></span>
                    </div>
                    
                    <ul class="bigger">
                        <li class=""><a href="https://secure.projectbubble.com/pricing">Signup</a></li>
                        <li class=""><a href="http://projectbubble.com/tour">About</a></li>
                        <li class=""><a href="http://projectbubble.com/">Introduction</a></li>
                    </ul>
                  
                    
            </div>              
            
            
        </div>

    </div>

    <div id="content" class="content ">

        <div class="container narrow">

            <div id="tpl-manage" class="clearfix">

    <div id="tpl-signup">
        
        <div class="tpl-2col">
        
            <div class="col1 bigmargin">
            
                                
                <div class="box">                  
                      <a href='<portlet:renderURL><portlet:param name="action" value="forward"/>
           </portlet:renderURL>'>Render Forward</a>
  <a href='<portlet:actionURL><portlet:param name="action" value="forward"/>
           </portlet:actionURL>'>Action Forward</a>
             
                    <form:form method="post" commandName="loginForm" action="${formAction}" class="default biglabel">
                    
                                    
                      
                        <input type="submit" id="login" name="login" value="Forward" class="button big">
                                         
                            
                        
                    </form:form>
            
                            </div>
                
                <div id="holdon">
                
                                        
                </div>
                
            </div>
            
        </div>
        
    </div>

        </div>

        
        </div>

    </div>

    <div id="footer" class="content clearfix">

        <div class="container">
        
                        
                <p class="links">
                    <a href="https://oopms.googlcode.com/privacy">Privacy Policy</a> |
                    <a href="https://oopms.googlcode.com/terms-of-service">Terms of Service</a> |
                    <a href="https://oopms.googlcode.com/help">Help</a>
                </p>
                
                <p class="copyright">
                    <a href="http://projectbubble.com/"><img src="./css/marque.png" class="marque" alt="Project Bubble"></a>
                    © 2012 Project Bubble LLC
                </p>
                                
                        
        </div>
        
    </div>


</div>


<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-6656902-4']);
  _gaq.push(['_setDomainName', 'projectbubble.com']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>


</body></html>