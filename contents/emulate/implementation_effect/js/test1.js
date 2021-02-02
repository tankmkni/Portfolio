/**************
 *  test1.js  *
 **************/

function test1(){
    document.querySelector('.inner').onmousemove = ( ee ) => {

        const xx = ee.pageX - ee.target.offsetLeft
        const yy = ee.pageY - ee.target.offsetTop
        
        ee.target.style.setProperty('--x', `${ xx }px`)
        ee.target.style.setProperty('--y', `${ yy }px`)
    }
}