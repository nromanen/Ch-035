<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
jQuery.extend(jQuery.validator.messages, {
	    required: "<spring:message code="crsms.error.field.required"/>",
	    maxlength: "<spring:message code="crsms.error.field.too.long"/>",
	    minlength: "<spring:message code="crsms.error.too.short"/>",
	    regex: "<spring:message code="crsms.error.field.letters.only"/>"
});
</script>