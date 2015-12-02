<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
jQuery.extend(jQuery.validator.messages, {
	    required: "<spring:message code="crsms.error.field.required"/>",
	    maxlength: "<spring:message code="crsms.error.field.too.long"/>",
	    minlength: "<spring:message code="crsms.error.too.short"/>",
	    regexSearch: "<spring:message code="crsms.error.field.forbidden"/>",
	    email: "<spring:message code="crsms.error.email.invalid"/>",
	    equalTo: "<spring:message code="crsms.error.password.not.equals"/>",
	    regexName: "<spring:message code="crsms.error.name.invalid"/>",
	    min: "<spring:message code="crsms.error.number.min"/>",
	    max: "<spring:message code="crsms.error.number.max"/>",
	    url: "<spring:message code="crsms.error.url"/>",
	    accept: "<spring:message code="crsms.error.file.type"/>",
	    pattern: "<spring:message code="crsms.error.format.invalid"/>",
	    regexEmail: "<spring:message code="crsms.error.email.invalid"/>"
});
</script>