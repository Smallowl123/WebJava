const xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
        parse(this)
    }
};
xhttp.open('GET',"Days.xml",true);
xhttp.send();

function parse(xml) {
    const xmlDoc = xml.responseXML;
    let date = xmlDoc.getElementsByTagName("date")
    let maxTemperature = xmlDoc.getElementsByTagName("maxTemperature")
    let minTemperature = xmlDoc.getElementsByTagName("minTemperature")
    let w_Event = xmlDoc.getElementsByTagName("w_Event")

    let table = document.querySelector("table")


    generateTable(table, date, maxTemperature, minTemperature, w_Event)
    let thead = table.createTHead();
    let row = thead.insertRow();
    createTableHead(row, "Date")
    createTableHead(row, "MAX, °C")
    createTableHead(row, "MIN, °C")
    createTableHead(row, "Event")

}

function createTableHead (row, key) {
    let th = document.createElement("th");
    let name = document.createTextNode(key);
    th.appendChild(name);
    row.appendChild(th);
    }
function generateTable(table, date, max, min, event){
    let data = [date, max, min]
    for (let i = 0; i<date.length; i++){
        let row = table.insertRow();
        for (let j = 0; j<data.length; j++){
            let cell = row.insertCell();
            let text = document.createTextNode(data[j][i].childNodes[0].nodeValue)
            cell.appendChild(text)
        }
        let ev = event[i].childNodes[0].nodeValue;
        let cell = row.insertCell();
        let src =   (ev == "Scattered showers") ? "icons/scattered.svg" :
                    (ev == "Rain")              ? "icons/rain.svg" :
                    (ev == "Partly cloudy")     ? "icons/partly_cloudy.svg" :
                                                  "icons/undefined.svg" ;
        let img = document.createElement("img");
        img.src = src;
        cell.appendChild(img);
    }
}
