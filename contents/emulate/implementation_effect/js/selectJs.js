/*****************
 *  selectJs.js  *
 *****************/

window.onload = () =>{

    const SELECTER = location.search.split('=')[1];
    document.getElementById("inner").innerHTML = SELECTER;

    switch( SELECTER ){
        case "test1": test1(); break;
        case "verificat": verificat(); break;
        case "fix": fix(); break;
    }
}