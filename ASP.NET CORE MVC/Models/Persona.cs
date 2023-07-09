using System;
using System.Collections.Generic;

namespace BIBLIOTECA_VIRTUAL.Models;

public partial class Persona
{
    public int IdPersona { get; set; }

    public int IdTipoPersona { get; set; }

    public int IdDistrito { get; set; }

    public string Nombre { get; set; } = null!;

    public string Apellido { get; set; } = null!;

    public string Dni { get; set; } = null!;

    public string Telefono { get; set; } = null!;

    public string CorreoElec { get; set; } = null!;

    public virtual Distrito IdDistritoNavigation { get; set; } = null!;

    public virtual TipoPersona IdTipoPersonaNavigation { get; set; } = null!;

    public virtual ICollection<RegistroLibrosEditorial> RegistroLibrosEditorials { get; set; } = new List<RegistroLibrosEditorial>();

    public virtual ICollection<ReservaVirtual> ReservaVirtuals { get; set; } = new List<ReservaVirtual>();

    public virtual Usuario? Usuario { get; set; }
}
