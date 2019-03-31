
import client from '../client';

class Requests{
    getNewSession(data){
        return new Promise((resolve,reject) => {
            client({method: 'GET', path: '../new'}).done(function(response){
                resolve([]);
            },
            function(response) {
                resolve([]);
            });
        });
    }


};

export default Requests;