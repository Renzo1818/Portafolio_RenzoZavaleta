using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class EditorialRepository : IEditorial
    {
        private OperaC conexion = new OperaC();
        public void AñadiEditorial(Editorial objEditorial)
        {
            conexion.Editorials.Add(objEditorial);
            conexion.SaveChanges();
        }

        public void EditDetails_Editorial(Editorial objEditorial)
        {
            var objModificar = (from tbEditorial in conexion.Editorials where tbEditorial.IdEditorial == objEditorial.IdEditorial select tbEditorial).FirstOrDefault();
            objModificar.RazonSocial = objEditorial.RazonSocial;
            objModificar.Ruc = objEditorial.Ruc;
            objModificar.Direccion  = objEditorial.Direccion;
            objModificar.CorreoElec = objEditorial.CorreoElec;

            conexion.SaveChanges();
        }

        public IEnumerable<Editorial> GetAllEditoriales()
        {
            return conexion.Editorials;
        }
        public IEnumerable<Editorial> GetAllEditorialesTrue()
        {
            var obj = (from tbEditorial in conexion.Editorials where tbEditorial.Estado == true select tbEditorial).ToList();
            return obj;

        }

        public int GetCorrelativoEditorial()
        {
            return conexion.Editorials.Max(e => e.IdEditorial);
        }

        public Editorial GetIdEditorialModificar(int codigo)
        {
            var obj = (from tbEditorial in conexion.Editorials where tbEditorial.IdEditorial.Equals(codigo) select tbEditorial).Single();
            return obj;
        }

        public bool EliminarEditorial(int codigo)
        {
            var objModificar = (from tbEditorial in conexion.Editorials where tbEditorial.IdEditorial == codigo select tbEditorial).FirstOrDefault();

            if (objModificar != null)
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

        public Editorial Buscar(string buscar)
        {
            if (!String.IsNullOrEmpty(buscar))
            {
                var obj = (from tbEditorial in conexion.Editorials where tbEditorial.RazonSocial == buscar select tbEditorial).FirstOrDefault();

                return obj;
            }
            else
            {
                return null;
            }
        }
    }
}
