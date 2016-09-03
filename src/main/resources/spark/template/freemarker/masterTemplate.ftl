<#macro masterTemplate title="Welcome">
    <!DOCTYPE html
            PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <title>${title} | Action Item Tracker</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
    </head>
    <body>
		<div class="page">
	  		<h1>ActiTrack - Action Item Tracker</h1>
		  	<div class="navigation">
		  	<#if user??>
		    	<a href="/create">Create</a> |
		    	<a href="/">Assigned Items</a> |
		    	<a href="/submitted">Submitted Items</a> |
		    	<!-- <a href="/timeline">my timeline</a> |
		    	<a href="/public">public timeline</a> | -->
		    	<a href="/logout">sign out [${user.username}]</a>
		  	<#else>
			    <a href="/register">sign up</a> |
			    <a href="/login">sign in</a>
		  	</#if>
		  	</div>
		  	<div class="body">
		  		<#nested />
		  	</div>
		  	<div class="footer">
			    ActiTrack &mdash; Powered by Spark Java and Spring Framework
		  	</div>
		</div>
    </body>
    </html>
</#macro>
