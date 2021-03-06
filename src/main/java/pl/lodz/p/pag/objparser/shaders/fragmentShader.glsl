#version 330 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in float pass_isSelected;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColour;

void main(void){

    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDotl = dot(unitNormal, unitLightVector);
    float brightness = max(nDotl, pass_isSelected);
    vec3 diffuse = brightness * lightColour;

    out_Color = vec4(diffuse, 1.0) * texture(textureSampler, pass_textureCoords);
}