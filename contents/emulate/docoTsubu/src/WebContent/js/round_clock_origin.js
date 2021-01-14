var canvas = document.getElementById('clock');
var c = canvas.getContext('2d');

var center = 100;
var milli = getRadian(0.003);
var reverse = getRadian(180);

var longColor   = '#000';
var shortColor  = '#333';
var secondColor = '#666';
var centerColor = 'orange';

function draw(){

//	canvasの描画を消去
	c.clearRect( 0, 0, 300, 300 );

//	時間の情報を取得
	now		= new Date();
	hour 	= now.getHours();
	min		= now.getMinutes();
	sec		= now.getSeconds();
	mil		= now.getMilliseconds();

	if(hour > 11){
		hour -= 12;
	}

	hour = hour * 0.523 - Math.PI/2 + (min / 12 * 0.104);
	min  = min  * 0.104 - Math.PI/2;
	sec  = sec  * 0.104 - Math.PI/2;

//	短針
	c.strokeStyle = shortColor;
	c.lineCap = 'round';
	c.lineWidth = 7;
	line( 60, hour );

//	長針
	c.strokeStyle = longColor;
	c.lineWidth = 4;
	line( 75, min );

//	秒針
	c.strokeStyle = secondColor;
	c.lineWidth = 3;
	line( 75, sec );
//	秒針の先っちょ
	line( 20, sec + reverse );

	c.fillStyle = centerColor;
	circle(10);

}

function circle( size ){
	c.beginPath();
	c.arc( center, center, size, 0, Math.PI * 2, false );
	c.fill();
	c.closePath();
}

function line(size, angle){
	c.beginPath();
	c.moveTo( center, center );
	c.arc( center, center, size, angle, angle, false );
	c.stroke();
	c.closePath();
}

function getRadian(degree){
	return degree * Math.PI / 180;
}