using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IRegistro_Libro_Editorial
    {
        void AñadirRegistro_Libro_Editorial(RegistroLibrosEditorial objRegistro);
        RegistroLibrosEditorial GetIdRegistroModificar(int codigo);
        void EditDetails_Registro(RegistroLibrosEditorial objRegistro);
    }
}
