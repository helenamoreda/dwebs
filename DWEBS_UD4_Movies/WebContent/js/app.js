/**
Es necesario modificar esta url y apuntar a la URL del servicio web
*/

var BASE_URL = 'http://localhost:8080/DWEBS_UD4_Movies';
var THUMBNAIL_DEFAULT = "img/thumbnail.png";

var app = {

	init: function() {
		app.getPosts();
	},

	getPosts: function() {
		$.ajax({
			type: 'GET',
			//url: rootURL + '/movies?format=json',
			url: BASE_URL + '/movies',
			dataType: 'json',			
			success: function(data){
				// Recorrido elementos film
				var films = data.films.film;
				
				// Vaciado de subelementos del listview
				$('ul.post-list').empty();
				
				$.each(films, function(index, value) {
					console.log(index);
					console.log(value);
					
					// Chequear existencia de subelemento: thumbnail
					var thumbnailElement = value.thumbnail;
					
					if (!value.thumbnail)
						thumbnailElement = THUMBNAIL_DEFAULT;
					
					$('ul.post-list').append('<li>' +
					// Elemento del listview
					'<a href="#">'+
					'<img src="' + thumbnailElement + '" alt="album">' +
					'<h2>' + value.titulo +'</h2>' +
					'<p>' + value.director +' ('+ value.anio + ')</p>' +
					'</a>'+
					
					// Popup info básica de film
					'<a href="#e' + index + '" data-rel="popup" data-icon="info" data-position-to="window" data-transition="slideup">Info</a>' +
					'<div data-role="popup" id="e' + index +'" data-overlay-theme="b" data-theme="a" data-dismissible="true" style="max-width:400px;">' +
					// Contenedor de ficha de detalles de film
					'<div data-role="header" data-theme="a">' +
					'<h1>' + value.titulo + '</h1>' +
					'</div>' +
					'<div role="main" class="ui-content">' +
					'<h3 class="ui-title">'+ value.titulo +' ('+ value.anio + ')</h3>' +
					'<p>Director: ' + value.director + '</p>' +
					'<p>Duración: ' + value.minutos + ' minutos</p>' +
					'<p>Resumen: ' + value.resumen + '</p>' +
					'<a href="' + value.url + '" data-role="button" title="trailer">Trailer</a>' + 
					'</div>' + 
					'</div>' + 
					'</li>');
			    });
			},
			error: function(error){
				console.log(error);
			},
			complete: function() {
				// Refresco del listview al completar llamada AJAX
				$("ul.post-list").listview("refresh");
				$("ul.post-list").trigger("create");
	        } 
		});
	}
}