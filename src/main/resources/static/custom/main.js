
  function preparaDados(dados) {
      return dados.map(data => {
        result = (1-(cotacao.precoFechamento/cotacao.precoAbertura))*100;
                return  parseFloat(result.toFixed(2));          
      });
  };

  function setData(startDate,endDate){  // utilização de "closure"
        return function(name){
            return 'http://localhost:8084/api/b3/quotes/'+ name + '?start=' + startDate + '&end=' + endDate;
        };
  };

  function retornaDados(resp){
        return resp.json();
  }

  function render(valores){
        console.log(valores);
        renderMultipleChart(valores);
  }
  
  function displayError(error){
        $("#re-msg-error").append( `<p class='text-danger'>ERRO: ${error}</p>`);
  }

  let getUrl = setData('20200612','20200618');

//    const url = 'http://localhost:8084/api/b3/quotes/mglu3?start=20200612&end=20200617';

      const url = getUrl('MGLU3');
      const url1 = getUrl('BOVA11');
//      const url2 = getUrl('TEKA3');
      const ori = {Host: 'http://localhost:8084'};

      var request = new Request(url, { headers: new Headers({ 'Origin': 'http://localhost:8084' }) }); 
      var request1 = new Request(url1, { headers: new Headers({ 'Origin': 'http://localhost:8084' }) }); 

 //     Promise.all([fetch(url,ori).then(retornaDados), fetch(url1,ori).then(retornaDados)])
      Promise.all([fetch(request).then(retornaDados), fetch(request1).then(retornaDados)])
        .then(render)
        .catch(error => {
            console.log('Opss!! erro = ' + error);
            displayError(error);
        });

/*
    fetch(url)
        .then(retornaDados)
        .then(renderChart)
        .catch((error) => {
            console.log('Opps!! ' + error);
        });
*/
    var olddata = [0, 10, 5, 2, 20, 30, 45];
    var newdata = [10, 20, 30, 42, 50, 60, 70];

    var olddata1 = [99, 80, 75, 62, 50, 40, 35];
    var newdata1 = [5, 10, 55, 15, 20, 55, 25];

    var ctx = document.getElementById('myChart').getContext('2d');

function renderChart(data) {
    //        console.log(data);
            let resultado = data.map(cotacao => {
                result = (1-(cotacao.precoFechamento/cotacao.precoAbertura))*100;
                return  parseFloat(result.toFixed(2));
            });
//            console.log(resultado);
            var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',

    // The data for our dataset
    data: {
        labels: ["011", "02", "03", "04", "05", "06", "07"], 
        datasets: [{ 
              type: 'bar',
              label: 'MGLU3',
              backgroundColor: 'rgb(0, 99, 132)',
              borderColor: 'rgb(0, 99, 132)',
              data: resultado,
              order: 2,
            }, {
              type: 'line',
              label: 'BOVA11',
              borderColor: 'rgb(255, 0, 0)',
			  borderWidth: 1,
              fill: false,
              order: 1,
              data: [0.54, 2.34, 1.23, -4.55, 3.21, 5.55, 0.33]
            }
            /*
            ,{ 
              type: 'bar',
              label: 'OTHER',
              backgroundColor: 'rgb(0, 255, 0)',
              borderColor: 'rgb(0, 255, 0)',
              data:[0.23, 1.34, 6.23, 3.55, 0.21, 2.55, 1.33],
              order: 3,
            }
            */
            
            ]
    },
    // Configuration options go here
    options: {
        scales:{
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Dia'
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Ganho/Perda'
                }
            }]
        }
    }
//					responsive: true,
//					title: {
//						display: true,
//						text: 'Chart.js Combo Bar Line Chart'
//					},
//					tooltips: {
//						mode: 'index',
//						intersect: true
//					}
//				}
});
        }
//=====================================================================================================
function renderMultipleChart(resultados) {

    console.log(resultados);

    let resultadoPorAcao = resultados.map(resultadoStock =>{

        let resultado = resultadoStock.map(cotacao => {
                result = (1-(cotacao.precoFechamento/cotacao.precoAbertura))*100;
                return  parseFloat(result.toFixed(2));
            });

//            console.log(resultado);

            return resultado;
    })

    var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'bar',

    // The data for our dataset
    data: {
//        labels: ['01', '02', '03', '04', '05', '06', '07'],
        labels: [
            
                newDate(0),
                newDate(1),
                newDate(2),
                newDate(3),
                newDate(4),
                newDate(5),
                newDate(6)
                
                /*
                newDate1("20200612"),
                newDate1("20200613"),
                newDate1("20200614"),
                newDate1("20200615"),
                newDate1("20200616"),
                newDate1("20200617"),
                newDate1("20200618")
*/
        ],
        datasets: [{ 
              type: 'bar',
              label: 'MGLU3',
              backgroundColor: 'rgb(0, 99, 132)',
              borderColor: 'rgb(0, 99, 132)',
              data: resultadoPorAcao[0],
              order: 2,
            }, {
              type: 'line',
              label: 'BOVA11',
              borderColor: 'rgb(255, 0, 0)',
			  borderWidth: 1,
              fill: false,
              order: 1,
              data: resultadoPorAcao[1]
            }
            /*
            ,{ 
              type: 'bar',
              label: 'TEKA3',
              backgroundColor: 'rgb(0, 255, 0)',
              borderColor: 'rgb(0, 255, 0)',
              data:resultadoPorAcao[2],
              order: 3,
            }
            */
            ]
    },
    // Configuration options go here
    options: {
        scales:{
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Dia'
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Ganhos/Perdas'
                }
            }]
        }
    }
});
        }
//=====================================================================================================        

function newDate(days){
    //ref.: https://momentjs.com/docs/  e https://stackoverflow.com/questions/22547129/momentjs-date-string-add-5-days
//    return moment(moment().add('20200621', 'YYYYMMDD').toDate()).format('MMM DD');

    return moment(moment().add(days, 'd').toDate()).format('MMM DD');
}

function newDate1(date){
    //ref.: https://momentjs.com/docs/  e https://stackoverflow.com/questions/22547129/momentjs-date-string-add-5-days
    return moment(date).format('MMM DD');
}


function updateChart(){
//    chart.data.datasets[0].data = newdata;
//    chart.data.datasets[1].data = newdata1;
      chart.data.labels = ['August', 'September', 'October', 'November', 'December'];
    chart.update();
};

function addFirst(){
    chart.data.datasets[0].data.unshift(650);
    chart.update();
}

function addLast(){
    chart.data.datasets[0].data.push(700);
    chart.data.labels.push("January");
    chart.update();
}

function removeLast(){
    chart.data.labels.pop();
    chart.update();
}

function removeFirst(){
    chart.data.datasets[0].data.shift();
    chart.update();
}
 