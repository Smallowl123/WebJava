function rowDelete (date){
    let table = document.querySelector("table")
    for(let i = 0; i < table.rows.length; i++){
        if (table.rows[i].cells[0].innerHTML == date){
            table.rows[i].parentNode.removeChild(table.rows[i])
        }
    }
}
let delBtn = document.getElementById("delButton")
delBtn.onclick = function (){
    rowDelete(document.getElementById("del_input_date").value)
}

