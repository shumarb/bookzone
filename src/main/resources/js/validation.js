function validateForm() {
    var title = document.forms["addBookForm"]["title"].value;
    var author = document.forms["addBookForm"]["author"].value;
    var category = document.forms["addBookForm"]["category"].value;
    var year = parseInt(document.forms["addBookForm"]["year"].value);

    if (title === "" || author === "" || category === "") {
        alert("Title, author, and category cannot be empty");
        return false;
    }

    if (year !== "" && year <= 0) {
        alert("Please enter a valid year");
        return false;
    }

}
