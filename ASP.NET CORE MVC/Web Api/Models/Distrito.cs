﻿using System;
using System.Collections.Generic;

namespace API_BIBLIO.Models;

public partial class Distrito
{
    public int IdDistrito { get; set; }

    public string Descripcion { get; set; } = null!;

    public virtual ICollection<Persona> Personas { get; set; } = new List<Persona>();
}
