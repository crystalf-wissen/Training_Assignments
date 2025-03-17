import React from 'react';
import Header  from '../components/Header';
import Footer from '../components/Footer';
import Title from '../components/Title';

function Contact() {
    return (
        <div>
            <Header />
            <Title msg="CONTACT US PAGE"/>
            <p>Welcome to the Contact Page!</p>
            <Footer />
        </div>
    );
}

export default Contact;