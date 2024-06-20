import Routes from 'routes';
import ThemeCustomization from 'themes';
import ScrollTop from 'components/ScrollTop';
import { AuthProvider } from 'contexts/AuthContext';

const App = () => (
  <ThemeCustomization>
    <ScrollTop>
      <AuthProvider>
        <Routes />
      </AuthProvider>
    </ScrollTop>
  </ThemeCustomization>
);

export default App;
