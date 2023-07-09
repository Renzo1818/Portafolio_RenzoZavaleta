using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class Registro_Libro_EditorialRepository : IRegistro_Libro_Editorial
    {
        private OperaC conexion = new OperaC();
        public void AñadirRegistro_Libro_Editorial(RegistroLibrosEditorial objRegistro)
        {
            conexion.RegistroLibrosEditorials.Add(objRegistro);
            conexion.SaveChanges();
        }

        public RegistroLibrosEditorial GetIdRegistroModificar(int codigo)
        {
            var obj = (from tbRegistro in conexion.RegistroLibrosEditorials where tbRegistro.IdLibro == codigo select tbRegistro).Single();
            return obj;
        }

        public void EditDetails_Registro(RegistroLibrosEditorial objRegistro)
        {
            var objModificar = (from tbRegistro in conexion.RegistroLibrosEditorials where tbRegistro.IdLibro == objRegistro.IdLibro select tbRegistro).FirstOrDefault();
            objModificar.CantidadLibros = objRegistro.CantidadLibros;
            objModificar.FechaRegistro = objRegistro.FechaRegistro;

            conexion.SaveChanges();
        }
    }
}
