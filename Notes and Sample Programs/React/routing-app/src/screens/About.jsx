import React from 'react';
import Header  from '../components/Header';
import Footer from '../components/Footer';
import Title from '../components/Title';
function About() {
    return (
        <div>
            <Header />
            <Title msg="ABOUT OUR NATIONAL BANK"/>
            <p>Welcome to the About Page!</p>
            <Footer />
        </div>
    );
}

export default About;