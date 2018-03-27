import React, {Component} from 'react'

import ListofDeputies from './ListofDeputies'
import ClubChoice from './ClubChoice'

const clubMapping = {
    "Prawo i Sprawiedliwość" : "PIS",
    "Platforma Obywatelska" : "PO",
    "Nowoczesna" : "Nowoczesna",
    "Polskie Stronnictwo Ludowe" : "PSL",
    "Kukiz'15" : "Kukiz'15",
    "Niezrzeszeni" : "Niezrzeszeni",
    "Wolni i Solidarni" : "WiS"
}

class DeputiesWrapper extends Component {
    constructor(props) {
        super(props);
        this.state = {
            clubs : this.convertToArr(clubMapping),
            clickedclubs : new Array(7).fill(null),
            clickedAny : false
        }
        console.log(this.state.clubs);
    }

    convertToArr = (clubMapping) => {
        var arr = [];
        for (var key in clubMapping) {
            if (clubMapping.hasOwnProperty(key)) {
                arr.push(clubMapping[key]);
            }
        }
        return arr;
    }

    handleClick = (index) => {
        if(this.state.clickedclubs[index] == null) {
            console.log(this.state.clickedclubs.slice(0, index));
            console.log(this.state.clubs[index]);
            console.log(this.state.clickedclubs.slice(index+1, this.state.clickedclubs.length));
            this.setState ({
                clickedclubs : [...this.state.clickedclubs.slice(0, index), this.state.clubs[index],  ...this.state.clickedclubs.slice(index+1, this.state.clickedclubs.length)],
                clickedAny : true
            })
        } else {
            this.setState ({
                clickedclubs : [...this.state.clickedclubs.slice(0, index), null, ...this.state.clickedclubs.slice(index+1, this.state.clickedclubs.length)],
                clickedAny : this.checkifAnyClicked(this.state.clickedclubs)
            })
        }
    }

    checkifAnyClicked = (clickedclubs) => {
        return (clickedclubs.includes(true));
    }

    determine = () => {
        return this.state.clickedAny ? this.state.clickedclubs : this.state.clubs;
    }

    render() {
        return(
            <div id = "wrapper">
                <ClubChoice clubMapping = {clubMapping} clubs = {this.state.clubs} onClick = {this.handleClick}/>
                <ListofDeputies clubMapping = {clubMapping} clubs = {this.state.clickedclubs}/>
            </div>
        )
    }
}

export default DeputiesWrapper;
