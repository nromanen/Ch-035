<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var = "addResourceLink" value = "add" />
<a class = "btn btn-primary" href = "${addResourceLink}">Add new resource</a>

<table class = "table table-bordered">
	<thead class = "">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Type</th>
			<th>url</th>
			<th>Controls</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var = "resource" items = "${resources}">
			<tr>
				<th>${resource.id}</th>
				<td>${resource.name}</td>
				<td>${resource.type}</td>
				<td>${resource.url}</td>
				<td>
					<div class="btn-toolbar" role="toolbar">
					  <c:url var = "editResourceLink" value = "${resource.id}/edit" />
					  <a href = "${editResourceLink}" class="btn btn-primary btn-sm" 
					  		data-toggle="tooltip" title="Edit Resource" >
					    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
					  </a>	
					  

					  <button class="btn btn-danger btn-sm btn-delete-resource" 
					  		 data-toggle="tooltip" title="Delete Resource" 
					  		 value="${resource.id}" >
					    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					  </button>

					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:url var = "deleteResourceLink" value = "${resource.id}/delete" />
<!-- Modal delete confirmation -->
<div class="modal fade" id="delete-confirmation-modal" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Modal title</h4>
      </div>
      <div class="modal-body">
        <p>Are you sure?</p>
      </div>
      <div class="modal-footer">
      	<a href="#" id="btn-modal-delete-resource" class="btn btn-danger">Delete</a>
        <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<a class = "btn btn-primary" href = "${addResourceLink}">Add new resource</a>


