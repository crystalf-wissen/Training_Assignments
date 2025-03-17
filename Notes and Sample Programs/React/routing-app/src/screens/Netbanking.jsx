import React from 'react';
import Header  from '../components/Header';
import Footer from '../components/Footer';
import Title from '../components/Title';

function Netbanking() {
    return (
        <div>
            <Header />
            <Title msg="NETBANKING PAGE" />
            <h4>PLEASE LOGIN:</h4>
            <form>
                Username: <input /><br/>
                Password: <input /><br/>
                <br/>
                <button>LOGIN</button>
                <button>RESET</button>
            </form>
            <Footer />
        </div>
    );
}

export default Netbanking;