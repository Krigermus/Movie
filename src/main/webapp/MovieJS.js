window.onload = function () {

    function getAll() {
        let url = "http://www.melif.dk/Movie/api/Movie/getAllMovies";
        startFetch(url, "all");
    }

    function getData() {
        let url = "http://www.melif.dk/Movie/api/Movie/databaseData";
        startFetch(url, "data");
    }

    function getCount() {
        let url = "http://www.melif.dk/Movie/api/Movie/count";
        startFetch(url, "count");
    }

    function getId() {
        let url = "http://www.melif.dk/Movie/api/Movie/" + document.getElementById("idOrName").value;
        startFetch(url, "id");
    }

    function getName() {
        let url = "http://www.melif.dk/Movie/api/Movie/name/" + document.getElementById("idOrName").value;
        startFetch(url, "name");
    }


    function startFetch(url, type) {
        fetch(url)
            .then(res => res.json())
            .then(data => {
                switch (type) {
                    case "all":
                        document.getElementById("data").innerHTML = '';
                        document.getElementById("movie").innerHTML =
                            "<table><thead><tr><th>Id</th><th>Year</th><th>Name</th><th>Actors</th></tr></thead><tbody>" +
                            data.map(x => "<tr><td>" + x.id + "</td><td>" + x.year + "</td><td>" + x.name + "</td><td>" + x.actors.join(", ") + "</td></tr>").join('');
                        + "</tbody></table>";
                        break;
                    case "count":
                        document.getElementById("movie").innerHTML = '';
                        document.getElementById("data").innerHTML = data.count;
                        break;
                    case "data":
                        document.getElementById("movie").innerHTML = '';
                        document.getElementById("data").innerHTML = data.msg;
                        break;
                    case "id":
                        document.getElementById("data").innerHTML = '';
                        document.getElementById("movie").innerHTML =
                            "<table><thead><tr><th>Id</th><th>Year</th><th>Name</th><th>Actors</th></tr></thead><tbody>" +
                            "<tr><td>" + data.id + "</td><td>" + data.year + "</td><td>" + data.name + "</td><td>" + data.actors.join(", ") + "</td></tr></tbody></table>";
                        break;
                    case "name":
                        document.getElementById("data").innerHTML = '';
                        document.getElementById("movie").innerHTML =
                            "<table><thead><tr><th>Id</th><th>Year</th><th>Name</th><th>Actors</th></tr></thead><tbody>" +
                            data.map(x => "<tr><td>" + x.id + "</td><td>" + x.year + "</td><td>" + x.name + "</td><td>" + x.actors.join(", ") + "</td></tr>").join('');
                        + "</tbody></table>";
                        break;
                }
            })
    }
    document.getElementById("getAllMovies").onclick = getAll;
    document.getElementById("getMovieById").onclick = getId;
    document.getElementById("getMovieByName").onclick = getName;
    document.getElementById("getCount").onclick = getCount;
    document.getElementById("createData").onclick = getData;

}


