export const backendUrl:string="http://localhost:8080/";

export const Header = () => {
    const token = localStorage.getItem("token")? localStorage.getItem("token"):"";
    return({headers: {
        Authorization: 'Bearer ' + token //the token is a variable which holds the token
    }});
}