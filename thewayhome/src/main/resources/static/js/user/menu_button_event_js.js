document.getElementById("menuButton").addEventListener("click", function() {
    var menuContainer = document.getElementById("menuContainer");
    if (menuContainer.style.display === "none") {
        menuContainer.style.display = "block";
    } else {
        menuContainer.style.display = "none";
    }
});
