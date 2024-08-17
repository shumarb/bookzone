function validateForm() {
    var title = document.forms["addBookForm"]["title"].value;
    var author = document.forms["addBookForm"]["author"].value;
    var category = document.forms["addBookForm"]["category"].value;
    var year = parseInt(document.forms["addBookForm"]["year"].value);

    if (title === "" || author === "" || category === "" || isNaN(year) || year <= 0) {
        if (title === "" || author === "" || category === "") {
            alert("Please fill up all sections before submission.");
        } else if (isNaN(year) || year <= 0) {
            alert("Please enter a valid year");
        }
        return false;
    }

    return true;
}
