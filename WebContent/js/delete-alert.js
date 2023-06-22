// script.js
const deleteLinks = document.querySelectorAll('.delete-link');
deleteLinks.forEach(function(link) {
    link.addEventListener('click', function(event) {
        if (!confirm('この顧客を削除してもよろしいですか?')) {
            event.preventDefault();
        }
    });
});
