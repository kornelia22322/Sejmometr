import React, {Component} from 'react'
import {CSSTransitionGroup} from 'react-transition-group'
import {Link} from 'react-router-dom'



const URL_DEPUTIES = 'http://localhost:8080/';

class ListofDeputies extends Component {

    constructor(props){
        super(props);
        this.state = {
            deputies: [],
            filtered: [],
            keyword: ''
        }
    }

    componentDidMount() {
        fetch(`${URL_DEPUTIES}`)
        .then(response => response.json())
        .then(json => {
            this.setState({
                deputies: json,
                filtered: json
            })
            console.log(json.length);
            console.log(json);
        })
    }

    searchDeputy = (event) => {
        const keyword = event.target.value;
        console.log(event.target.value);
        if(keyword !== '') {
            const list = this.state.deputies.filter((item) => {
                return item.name.toLowerCase().indexOf(keyword.toLowerCase()) > -1;
            })
            this.setState({
                filtered: list,
                keyword: event.target.value
            })
        } else {
            this.setState({
                filtered: this.state.deputies,
                keyword: keyword
            })
        }
    }


    renderList = ({filtered}) => {
        console.log(filtered);
        return filtered.map((item) => {
            return (
                <div className="single_deputy" key={item.id}>
                    <Link to={`/deputy/${item.id}`} className="deputy_item" style={{ textDecoration: 'none' }}>
                    <p>{item.name}</p>
                    </Link>
                </div>
            )
        })
    }

    render() {
        return(
            <div className="deputies_component">
                <div className="search_component">
                    <input type="text" placeholder="Wyszukaj posła.."
                    onChange={(e) => this.searchDeputy(e)}/>
                </div>
                <div className="deputies_container">
                    {this.renderList(this.state)}
                </div>
            </div>
        )
    }
}

export default ListofDeputies;
