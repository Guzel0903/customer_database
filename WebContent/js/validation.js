function validateForm() {
    var name = document.getElementsByName("name")[0].value;
    var age = document.getElementsByName("age")[0].value;
    var address = document.getElementsByName("address")[0].value;
    
    if (name == "" || age == "" || address == "") {
        alert("すべての項目に入力してください。");
        return false;
    }
    
    if (isNaN(age) || age < 18 || age > 99) {
        alert("年齢は 18 から 99 までの数字である必要があります。ご確認ください。");
        return false;
    }
    
    return true;
}

