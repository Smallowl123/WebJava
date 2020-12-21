function addRow(date, max, min, event){
    let table = document.querySelector("table")
    let data = [date, max, min];
    let row = table.insertRow()
    for (let j = 0; j<data.length; j++){
        let cell = row.insertCell();
        let text = document.createTextNode(data[j])
        cell.appendChild(text)
    }
    let cell = row.insertCell()
    cell.appendChild(event)
}


let addBtn = document.getElementById("addButton")
addBtn.onclick = function (){
    let date = document.getElementById("input_date").value
    let max = document.getElementById("input_max").value
    let min = document.getElementById("input_min").value
    let radio = document.getElementsByName("Event")
    let src =   (radio[0].checked)     ? "icons/scattered.svg" :
                (radio[1].checked)     ? "icons/rain.svg" :
                (radio[2].checked)     ? "icons/partly_cloudy.svg" :
                (radio[3].checked)     ? "icons/undefined.svg" :
                "icons/undefined.svg" ;
    let event = document.createElement("img")
    event.src = src
    addRow(date, max, min, event)
}
