<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Timeline">
    <h2>${pageTitle}</h2>
    <#if user??>
    	<#if profileUser?? && user.id != profileUser.id>
    		<div class="followstatus">
    		<#if followed>
    			<a class="unfollow" href="/t/${profileUser.username}/unfollow">Unfollow user</a>
    		<#else>
    			<a class="follow" href="/t/${profileUser.username}/follow">Follow user</a>.
    		</#if>
    		</div>
    	<#elseif pageTitle != 'Public Timeline'>
    		<div class="twitbox">
        		<form action="/create" method="post">
          		<table>
                <tr>
                <td>Summary:</td>
                <td><input type="text" name="summary" size="50" maxlength="255"></td>
                <tr>
                <td>Description:</td>
                <td><textarea name="description"></textarea></td>
                </tr>
                <tr>
                <td>Due Date (Format: YYYY-MM-DD):</td>
                <td><input type="text" name="dueDate" maxlength="10"></td>
                </tr>
                <tr>
                <td>Assigned To:</td>
                <td>
                <select name="assignedTo">
                <#list users as u>
                    <option value="${u.id}">${u.username}</option>
                </#list>
                </select>
                </td>
                </tr>
                <tr>
                <td>Priority:</td>
                <td>
                <select name="priority">
                    <option value="High">High</option>
                    <option value="Medium">Medium</option>
                    <option value="Low">Low</option>
                </select>
                </td>
                </tr>
                <tr>
                <td>Status:</td>
                <td>
                <select name="status">
                    <option value="New">New</option>
                    <option value="In Progress">In Progress</option>
                    <option value="Completed">Completed</option>
                    <option value="Suspended">Suspended</option>
                </select>
                </td>
                </tr>
                <tr>
                <td colspan="2"><input type="submit" value="Submit"></td>
                </tr>
                </table>
        		</form>
      		</div>
    	</#if>
    </#if>
</@layout.masterTemplate>
