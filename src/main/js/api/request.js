
import client from '../client';

class Requests{
    getNewSession(data){
        return new Promise((resolve,reject) => {
            client({method: 'GET', path: './new'}).done(function(response){
                resolve(response.entity);
            },
            function(response) {
                resolve(response.entity);
            });
        });
    }

    addOperands(data){
        return new Promise((resolve,reject) => {
        client({
            method: 'POST',  
            headers: {
             'Content-Type': 'application/json' 
            } , 
            path: './operands',
            entity: data
            })
            .done(function(response){
                resolve(response.entity);
            },function(response){
                resolve(response.entity);
            });
        });
    }

    addOperation(data){
        return new Promise((resolve,reject) => {
        client({
            method: 'POST',
            path: './operation/'+data
            })
            .done(function(response){
                resolve(response.entity);
            },function(response){
                resolve(response.entity);
            });
        });
    }

    calculate(data){
        return new Promise((resolve,reject) => {
        client({
            method: 'GET',
            path: './result'
            })
            .done(function(response){
                resolve(response.entity);
            },function(response){
                resolve(response.entity);
            });
        });
    }
};

export default Requests;