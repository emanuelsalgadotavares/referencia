package br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.model.BasicEntity;

@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if (value == null || (value.trim().length() == 0))
            return null;
		
		if (value.matches("id:.+")) {
			
			Class<?> clazz = component.getValueExpression("value").getType(facesContext.getELContext());
			BasicEntity entidade = null;
			try {
				entidade = (BasicEntity) clazz.newInstance();
				String id = value.substring(3);
				Class<?> tipoId = (Class<?>) ((java.lang.reflect.ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
				if (tipoId.equals(String.class)) {
					entidade.setId(id);
				} else if (tipoId.equals(Long.class)) {
					entidade.setId(new Long(id));
				}
				return entidade;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//O valor selecionado não corresponde ao ID de uma entidade:
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if (object == null)
			return null;
		if (object instanceof BasicEntity<?>) {
			BasicEntity<?> entity = (BasicEntity<?>) object;
			return "id:" + entity.getId();
		}
		return object.toString();
	}

}
