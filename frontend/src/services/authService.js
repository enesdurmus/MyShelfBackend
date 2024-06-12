export const authService = {
    signin: async (email, password) => {
        // API çağrısı yaparak kullanıcıyı giriş yaptırın
        // Burada örnek olarak bir mock kullanıcı döndürüyoruz
        return { email };
    },
    signup: async (email, password) => {
        // API çağrısı yaparak kullanıcıyı kayıt ettirin
        // Burada örnek olarak bir mock kullanıcı döndürüyoruz
        return { email };
    },
    logout: () => {
        // Kullanıcıyı çıkış yaptırın
    }
};