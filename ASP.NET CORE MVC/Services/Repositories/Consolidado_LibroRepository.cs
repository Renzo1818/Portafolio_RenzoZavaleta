using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class Consolidado_LibroRepository:IConsolidadoLibro
    {
        private OperaC conexion = new OperaC();
        public IEnumerable<ConsolidadoLibro> GetAllCatalogo()
        {
           
            var obj = from viewConso in conexion.ConsolidadoLibros
                      select new ConsolidadoLibro
                      {
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Categoria = viewConso.Categoria,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                          Estado = viewConso.Estado,
                      };    
                       
            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoAccion()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros where viewConso.Categoria == "Acción" && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoAventura()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.Categoria == "Aventura" && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoCuento()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.Categoria == "Cuento" && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoEdit()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros where viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          Titulo = viewConso.Titulo,
                          Categoria = viewConso.Categoria,
                          Autor = viewConso.Autor,
                          Editorial = viewConso.Editorial,
                          Stock = viewConso.Stock,
                          Cantidad = viewConso.Cantidad,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoInfantil()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.Categoria == "Infantil" && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoNovela()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.Categoria == "Novela" && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoPoesia()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.Categoria == "Poesia" && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoRecientemente()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.FechaRegistro >= new DateTime(2023, 06, 01) && viewConso.FechaRegistro <= new DateTime(2023, 06, 30) && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoRomance()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                       where viewConso.Categoria == "Romance" && viewConso.Estado == true
                      select new ConsolidadoLibro
                       {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                           Titulo = viewConso.Titulo,
                           Autor = viewConso.Autor,
                           FechaPublicacion = viewConso.FechaPublicacion,
                       };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoTerror()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.Categoria == "Terror" && viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro = viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }

        public IEnumerable<ConsolidadoLibro> GetAllCatalogoView()
        {
            var obj = from viewConso in conexion.ConsolidadoLibros
                      where viewConso.Estado == true
                      select new ConsolidadoLibro
                      {
                          IdLibro=viewConso.IdLibro,
                          RutaImagen = viewConso.RutaImagen,
                          Titulo = viewConso.Titulo,
                          Autor = viewConso.Autor,
                          Categoria = viewConso.Categoria,
                          FechaPublicacion = viewConso.FechaPublicacion,
                      };

            return obj.ToList();
        }


        public ConsolidadoLibro GetIdLibro(int codigo)
        {
            var obj = (from viewConso in conexion.ConsolidadoLibros where viewConso.IdLibro == codigo select viewConso).FirstOrDefault();
            return obj;
        }

        public int GetTituloIdLibro(string titulo)
        {
            var objId = (from viewConso in conexion.ConsolidadoLibros
                         where viewConso.Titulo == titulo && viewConso.Estado == true
                         select new ConsolidadoLibro
                         {
                             IdLibro = viewConso.IdLibro,
                         }).FirstOrDefault();

            if (objId != null)
            {
                return objId.IdLibro;
            }

            return -1;
        }
        public IEnumerable<ConsolidadoLibro>? GetAutorLibro(string autor)
        {
            var objEncontrado = (from viewConso in conexion.ConsolidadoLibros
                         where viewConso.Autor == autor && viewConso.Estado == true
                         select new ConsolidadoLibro
                         {
                             IdLibro = viewConso.IdLibro,
                             RutaImagen = viewConso.RutaImagen,
                             Titulo = viewConso.Titulo,
                             Autor = viewConso.Autor,
                             Categoria = viewConso.Categoria,
                             FechaPublicacion = viewConso.FechaPublicacion,
                         });

            return objEncontrado.ToList();
        }
        public IEnumerable<ConsolidadoLibro>? GetCategoriaIdLibro(string categoria)
        {
            var objEncontrado = (from viewConso in conexion.ConsolidadoLibros
                         where viewConso.Categoria == categoria && viewConso.Estado == true
                         select new ConsolidadoLibro
                         {
                             IdLibro = viewConso.IdLibro,
                             RutaImagen = viewConso.RutaImagen,
                             Titulo = viewConso.Titulo,
                             Autor = viewConso.Autor,
                             Categoria = viewConso.Categoria,
                             FechaPublicacion = viewConso.FechaPublicacion,
                         });

            return objEncontrado.ToList();
        }

        public IEnumerable<ConsolidadoLibro>? GetCateAutorIdLibro(string categoria, string autor)
        {
            var objEncontrado = (from viewConso in conexion.ConsolidadoLibros
                                 where viewConso.Categoria == categoria && viewConso.Autor == autor && viewConso.Estado == true
                                 select new ConsolidadoLibro
                                 {
                                     IdLibro = viewConso.IdLibro,
                                     RutaImagen = viewConso.RutaImagen,
                                     Titulo = viewConso.Titulo,
                                     Autor = viewConso.Autor,
                                     Categoria = viewConso.Categoria,
                                     FechaPublicacion = viewConso.FechaPublicacion,
                                 });

            return objEncontrado.ToList();
        }

        public int GetTituCateAutoIdLibro(string titulo, string categoria, string autor)
        {
            var objId = (from viewConso in conexion.ConsolidadoLibros
                         where viewConso.Titulo == titulo && viewConso.Categoria == categoria && viewConso.Autor == autor && viewConso.Estado == true
                         select new ConsolidadoLibro
                         {
                             IdLibro = viewConso.IdLibro,
                         }).FirstOrDefault();

            if (objId != null)
            {
                return objId.IdLibro;
            }

            return -1;
        }
    }
}
