<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Timeline">
    <h2>${pageTitle}</h2>
    <ul class="messages">
    <#if items??>
    <#list items as i>
		<li>
        <p>
		${i.summary}
        <br>
		<small>${i.description}</small>
        <br>
        Due On: <em>${i.dueDate}</em>
        <br>
        Status: <em>${i.status}</em> &nbsp;&nbsp;
        Priority: <em>${i.priority}</em> &nbsp;&nbsp
        Assigned To: <em>${i.assignedToStr}</em>
        <br>
		<small>Created by: ${i.createdByStr}</small>
        </p></li>
	<#else>
		<li><em>There are no Action Items</em>
	</#list>
	<#else>
		<li><em>There are no Action Items</em>
	</#if>
	</ul>
</@layout.masterTemplate>
