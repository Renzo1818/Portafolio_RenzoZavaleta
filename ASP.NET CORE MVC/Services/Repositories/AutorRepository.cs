using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class AutorRepository : IAutor
    {
        private OperaC conexion = new OperaC();
        public void AñadirAutor(Autor objAutor)
        {
            conexion.Autors.Add(objAutor);
            conexion.SaveChanges();
        }

        public void EditDetails_Autor(Autor objAutor)
        {
            var objModificar = (from tbAutor in conexion.Autors where tbAutor.IdAutor == objAutor.IdAutor select tbAutor).FirstOrDefault();
            objModificar.NombreApellido = objAutor.NombreApellido;
            objModificar.FechaNacimiento = objAutor.FechaNacimiento;
            objModificar.Nacionalidad = objAutor.Nacionalidad;

            conexion.SaveChanges();
        }

        public IEnumerable<Autor> GetAllAutores()
        {
            return conexion.Autors;
        }

        public IEnumerable<Autor> GetAllAutoresTrue()
        {
            var obj = (from tbAutor in conexion.Autors where tbAutor.Estado == true select tbAutor).ToList();
            return obj;

        }

        public int GetCorrelativoAutor()
        {
            return conexion.Autors.Max(a => a.IdAutor);
        }

        public Autor GetIdAutorModificar(int codigo)
        {
            var obj = (from tbAutor in conexion.Autors where tbAutor.IdAutor.Equals(codigo) select tbAutor).Single();
            return obj;
        }

        public bool EliminarAutor(int codigo)
        {
            var objModificar = (from tbAutor in conexion.Autors where tbAutor.IdAutor == codigo select tbAutor).FirstOrDefault();
            
            if(objModificar != null)
            {
                objModificar.Estado = false;
                conexion.SaveChanges();

                return true;
            }
            else
            {
                return false;
            }
        }

        public Autor Buscar(string buscar)
        {
            if (!String.IsNullOrEmpty(buscar))
            {
                var obj = (from tbAutor in conexion.Autors where tbAutor.NombreApellido == buscar select tbAutor).FirstOrDefault();

                return obj;
            }
            else
            {
                return null;
            }
        }
    }
}
