function searchBoxPetConfirmAdmin() {
    console.log('searchBoxPetConfirmAdmin() CALLED!!');

    var searchOption = document.getElementById("search_option").value;
    var sNameInput = document.getElementById("search").value;
    var s_no = document.getElementById("s_no").value;

  /*  var searchOption = $('#search_option').text();
    var sNameInput = $('#search').text();
    var s_no = $('#s_no').text();*/

    console.log("searchOption: " + searchOption);
    console.log("sNameInput: "  + sNameInput);
    console.log("s_no: " + s_no);


    /*if(searchOption == null)*/
    location.href= "/admin/pets/pets_list?s_no="+s_no+"&searchOption="+searchOption+"&sNameInput="+sNameInput;



}