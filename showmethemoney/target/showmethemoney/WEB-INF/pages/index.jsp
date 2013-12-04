<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.2//EN" "http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>ShowMeTheMoney</title>
	<meta name="HandheldFriendly" content="true">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0;">
	<meta name="apple-mobile-web-app-capable" content="true" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	
	<%@ include file="/WEB-INF/pages/index-css.jsp"%>
	<script type="text/javascript">
		window.addEventListener("load",function() {
    		window.scrollTo(0, 0);
		});
	</script>
</head>

<body>
<div class="wrapper">
	<img class="header" src="http://where-spotlight.s3.amazonaws.com/test/Show%20Me%20The%20Money.jpg" alt="PayPal"  />
	
	<div class="footer"></div>
	
	<div class="content">
		<form action="signup" method="get"> 
			<label>Email Address</label>
			<input type="text" name="email" value="" id="email_address" />
			
			<label>Zip Code</label>
			<input type="text" name="zip" value="" id="zip_code"/>
			<br/>
			<input type="submit" id="submit" class="sign-up" value="Sign-up"/> 
		</form> 	 	 	
	</div>
	
	<div class="footer"></div>
</div>	
</body>
</html>
<!-- implement row/column. 
	 improve ui
 -->