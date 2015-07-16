var express = require('express');
var router = express.Router();
var mysql = require('mysql');

var connection = mysql.createConnection({
	host: 'localhost',
	port: 3306,
	user: 'jojo',
	password: 'abcd',
	database: 'eight'
});

connection.connect(function(err) {
	if(err) {
		console.error('mysql connection error');
		console.error(err);
		throw err;
	}
});

/* GET home page. */
router.get('/', function(req, res, next) {
	var query = connection.query('select * from comics', function(err, rows){
  		res.render('index', { title: 'Express', comics: rows });
		// res.json(rows);
	})
});

module.exports = router;

