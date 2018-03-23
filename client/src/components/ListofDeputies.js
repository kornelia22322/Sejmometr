import React, {Component} from 'react'
import {CSSTransitionGroup} from 'react-transition-group'
import {Link} from 'react-router-dom'

const fadeAnimation = {
    transitionName: 'fade',
    transitionAppear: true,
    transitionAppearTimeout: 500,
    transitionEnter: true,
    transitionEnterTimeout: 500,
    transitionLeave: true,
    transitionLeaveTimeout: 500
}

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
            this.setState({data: json})
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
                <div class="single_deputy">
                    <Link to={`/deputy/${item.id}`} key={item.id} className="deputy_item">
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
                    onChange={(e) => this.searchTeam(e)}/>
                </div>
                <div className="deputies_container">
                    <CSSTransitionGroup {...fadeAnimation}>
                        {this.renderList(this.state)}
                    </CSSTransitionGroup>
                </div>
            </div>
        )
    }
}

export default ListofDeputies;
