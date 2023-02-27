import axios from "axios";

export default class PostService {
    static async getAll() {
        try {
            const response = await axios.get('https://jsonplaceholder.typicode.com/photos')
            return response
        } catch (err) {
            console.log(err)
        }
    }
}