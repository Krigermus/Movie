window.onload = function() {

    function getAll() {
        let url = "https://melif.dk/Movie/api/Movie/getAllMovies";
        startFetch(url, "all");
    }


    function startFetch(url, type) {
        switch (type) {
            case "all":
                fetch(url)
                    .then(res => res.json())
                    .then(data => {
                        document.getElementById("movie").innerHTML =
                        "<table><thead><tr><th>Id</th><th>Year</th><th>Name</th></tr><th>Actors</th></thead><tbody>" + 
                        data.map(x => "<tr><td>" + x.id + "</td><td>" + x.year +"</td><td>" + x.name +"</td><td>" + x.actors.join() +"</td></tr>").join('');
                        + "</tbody></table>";
                    })
                }
            }
document.getElementById("getAllMovies").onclick = getAll;

}


