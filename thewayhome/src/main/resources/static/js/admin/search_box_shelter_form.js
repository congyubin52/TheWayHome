function searchBoxShelterConfirm() {
    console.log('searchBoxShelterConfirm() CALLED!!');

    /*let form = document.search_box_shelter_form;
    if (form.s_name.value == '') {
        alert('검색어를 입력해주세요.');
        location.href= "/user/pets/shelter_list";
        form.s_name.focus();

    } else {
        form.submit();

    }
*/

    var searchOption = document.getElementById("search_option").value;
    var sNameInput = document.getElementById("s_name").value;

    location.href= "/user/pets/shelter_list?searchOption="+searchOption+"&sNameInput="+sNameInput;


}