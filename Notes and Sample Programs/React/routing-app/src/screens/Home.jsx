import React from 'react';
import Header  from '../components/Header';
import Footer from '../components/Footer';
import Title from '../components/Title';
import { useNavigate } from 'react-router-dom';
function Home() {
    let flag = false;
    const navigate = useNavigate();

    function goTo(){
        if (flag) {
            navigate('/login')
        } else {
            navigate('/about');
        }
        flag =! flag;
    }

    return (
        <div>
            <Header />
            <Title msg="WELCOME TO OUR NATIONAL BANK"/>
            <p>Welcome to the Home Page!</p>
            <button onClick={goTo}>Toggle Page</button>
            <Footer />
        </div>
    );
}

export default Home;