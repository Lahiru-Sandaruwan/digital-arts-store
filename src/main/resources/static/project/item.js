$(document).ready(function() {
    $("form").submit(function(e){
        e.preventDefault();
    });
    viewItems();
});

function saveItems() {
    var itemCode = $("#itemCode").val();
    var itemName = $("#itemName").val();

    var fileInput = document.getElementById('fileInput');
            var file = fileInput.files[0];

            var filenameInput = document.getElementById('filenameInput');
            var filename = filenameInput.value;

            var formData = new FormData();
            formData.append('file', file);
            formData.append('filename', filename);

    // Add other fields to the formData
    formData.append('itemCode', itemCode);
    formData.append('itemName', itemName);


    $.ajax({
        url: "item/",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function (data) {
            alert("Successfully Saved");
            location.reload();
        },
        error: function (data) {
            var err = eval("(" + data.messages + ")");
            alert(err.message);
            console.log(data.success);
        }
    });
}

function viewItems(){
        $.ajax({
				url : "item/items",
				type : "GET",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				dataType : 'json',
				success: function (responseText) {
                	    for (i = 0; i<responseText.length; i++) {
                	    $('#guest_tbody').append(
                		    	    "<tr>"
                		    	    +"<td class=\"hide_me\">"
                                    +responseText[i].itemId
                                    +"</td>"
                		            +"<td>"
                		            +responseText[i].itemCode
                		    		+"</td>"
                		            +"<td>"
                		            +responseText[i].itemName
                		    		+"</td>"
                		    		+"<td>"
                                    + "<img src='data:image/png;base64," + responseText[i].content + "' alt='Image' width='100' height='100'>"
                                    +"</td>"
                                    +"<td>"
                                    +"<div class=\"d-flex\">"
                                    +"<button onclick=\"editItems("+responseText[i].itemId+")\" class=\"btn btn-primary shadow btn-xs sharp me-1\"><i class=\"fa fa-pencil-alt\"></i>edit</button>"
                                    +"<button onclick=\"showDeleteItem("+responseText[i].itemId+")\" class=\"btn btn-danger shadow btn-xs sharp\"><i class=\"fa fa-trash\"></i>delete</button>"
                                    +"</div>"
                                    +"</td>"

                		    	    +"</tr>" );

                	    }
                         $('#guest_table').DataTable({
                                       "aaSorting": []
                                    });

				},
				error : function(e) {
					// alert(e);
					console.log(e)
				}
			});
}
function showDeleteItem(hotelId){
$("#delete").modal('show');
$("#deleteItemId").val(hotelId);
}

function deleteItem() {

	$.ajax({
		url : "item/" + $("#deleteItemId").val(),
		type : "DELETE",
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		dataType : "json",
		success : function(data) {
                //alert("error");
		},
		error : function(data) {
			var err = eval("(" + data.responseText + ")");
			alert("error");
			console.log(data.success);
		}
	});
	location.reload();
}

function editItems(itemId){
    $("#edit").modal('show');
     $.ajax({
            url: "hotel/"+hotelId,
            type:"GET",
            headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'},
            dataType:"json",
            success: function (responseText) {
                $("#edithotelId").val(responseText.payload.hotelId);
                $("#edithotelCode").val(responseText.payload.hotelCode);
                $("#edithotelName").val(responseText.payload.name);
                $("#edithotelAddress").val(responseText.payload.address);
                $("#edithotelContactNo").val(responseText.payload.contactNo);
                $("#edithotelEmail").val(responseText.payload.email);
                }
            });

}

