/**
 * Created by max on 2/11/17.
 */

function getCost(){
    $('#loadingmessage').show();  // show the loading message.
    $.when(order()).done(getCost2);
    $('#loadingmessage').hide(); // hide the loading message
}

function getCost2() {
    $.ajax({
        url: 'http://localhost:9000/book/order-cost/',
        dataType: 'json',
        success: function (data) {
            data = data.data.totalPrice;
            $("#totalCost").html('<div id="TotalCost">' + data + '</div>')
        },
        error: function (data) {
            var error = "<div class='alert alert-danger'> " +
                "<strong> data.data</strong>" +
                " </div>"
            $('#totalCost').html(error)
        }
    });
}

function loadAllBooks() {
    $.ajax({
        url: 'http://localhost:9000/book/get-all',
        dataType: 'json',
        success: function (data) {
            var books = "<tbody";
            $.each(data.data, function (i, book) {
                var button = "<button onclick='loadBook(" + book.id + ")' type='button' class='btn btn-primary btn-lg'>Show</button>";
                var deleteButton = "<button onclick='deleteBook(" + book.id + ")' type='button' class='btn btn-primary btn-lg'>Delete</button>";
                var addToCartButton = "<button onclick='addToCart(" + book.id + ")' type='button' class='btn btn-primary btn-lg'>Add to Cart</button>";
                books +=
                    "<tr><td>" +
                    book.authorsNames + "</td>" + "<td>" + book.bookTitle + "</td>" +
                    "<td>" + button + "</td>" +
                    "<td>" + deleteButton + "</td>" +
                    "<td>" + addToCartButton + "</td>" +
                    "<td row.id='" + book.id + "'>" + getCountFromCart(book.id) + "</td>" +
                    "</tr>"
            });
            books += "</tbody>"
            $('#books tbody').html(books);
            $('#booksError').html("<div id='booksError' </div>")
        },
        error: function () {
            var error = "<div class='alert alert-danger'> " +
                "<strong> Could not connect to server</strong>" +
                " </div>"
            $('#booksError').html(error)
        }
    })
    ;
}

var cart = {};

function getCountFromCart(id) {
    if (cart[id] == undefined) return 0;
    else return cart[id];
}

function addToCart(id) {
    if (cart[id] == undefined) {
        cart[id] = 1
    }
    else cart[id] = cart[id] + 1
    loadAllBooks()
}

function loadBook(id) {
    $.ajax({
        url: 'http://localhost:9000/book/' + id,
        dataType: 'json',
        success: function (data) {
            data = data.data
            var book = '<div id="oneBook">' +
                '<iframe name="votar" style="display:none;"></iframe>' +
                '<form id="editBookForm" action="#" onsubmit="editBook()" target="votar"> ' +
                'Id: ' + data.id + '<input type="hidden" name="id" value="' + data.id + '"><br>' +
                'Authors: <input type="text" name="authorsNames" value="' + data.authorsNames + '"><br>' +
                'Title: <input type="text" name="bookTitle" value="' + data.bookTitle + '"><br>' +
                'Genre: <input type="text" name="genre" value="' + data.genre + '"><br>' +
                'Page Count: <input type="text" name="pageCount" value="' + data.pageCount + '"><br>' +
                '<input type="submit" value="Submit">' +
                '</form></div>'
            $('#oneBook').html(book);
            $('#bookError').html("<div id='bookError' </div>")
        },
        error: function () {
            var error = "<div class='alert alert-danger'> " +
                "<strong> Could not connect to server</strong>" +
                " </div>"
            $('#bookError').html(error)

        }
    });
}

function deleteBook(id) {
    $.ajax({
        url: 'http://localhost:9000/book/' + id,
        type: 'DELETE',
        success: function () {
            loadAllBooks();
            $('#oneBook').html('<table id="oneBook" class="table table-striped" data-sort-order="desc" data-sort-name="field3">')
            $('#bookError').html("<div id='bookError' </div>")
        },

        error: function () {
            var error = "<div class='alert alert-danger'> " +
                "<strong> Server error </strong>" +
                " </div>"
            $('#bookError').html(error)
        }
    });
}

function addNewBook() {
    var x = $('#addNewBookForm')
    var id = x[0][0].value
    var authors = x[0][1].value
    var title = x[0][2].value
    var genre = x[0][3].value
    var pageCount = x[0][4].value
    $.ajax({
        url: 'http://localhost:9000/book/new',
        type: 'PUT',
        contentType: "application/json",
        data: JSON.stringify(
            {
                authorsNames: authors,
                bookTitle: title,
                genre: genre,
                pageCount: pageCount
            }),
        success: function (data) {
            if (data && !data.ok) {
                var error = "<div class='alert alert-danger'> " +
                    "<strong> " + data.data + "</strong>" +
                    " </div>"
                $('#booksError').html(error)
                return
            }
            $('#booksError').html("<div id='booksError' </div>")
        },
        error: function () {
            var error = "<div class='alert alert-danger'> " +
                "<strong> Could not connect to server</strong>" +
                " </div>"
            $('#booksError').html(error)
        }
    })
}

function order() {
    $.ajax({
        url: 'http://localhost:9000/book/order-books/',
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(cart),
    });
}

function editBook() {
    var x = $('#editBookForm')
    var id = x[0][0].value
    var authors = x[0][1].value
    var title = x[0][2].value
    var genre = x[0][3].value
    var pageCount = x[0][4].value
    $.ajax({
        url: 'http://localhost:9000/book/edit',
        type: 'POST',
        contentType: "application/json",
        data: JSON.stringify(
            {
                id: parseInt(id),
                authorsNames: authors,
                bookTitle: title,
                genre: genre,
                pageCount: pageCount
            }),
        success: function (data) {
            if (data && !data.ok) {
                var error = "<div class='alert alert-danger'> " +
                    "<strong>" + data.data + "</strong>" +
                    " </div>"
                $('#bookError').html(error)
                return
            }
            $('#bookError').html("<div id='bookError' </div>")

        }
    })

}