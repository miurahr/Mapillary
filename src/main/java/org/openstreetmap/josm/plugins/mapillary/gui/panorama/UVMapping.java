// License: GPL. For details, see LICENSE file.
// SPDX-License-Identifier: GPL-2.0-or-later
package org.openstreetmap.josm.plugins.mapillary.gui.panorama;

import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Math;


public final class UVMapping {
  private UVMapping() {
    // Private constructor to avoid instantiation
  }
  /**
   * Returns the point of the texture image that is mapped to the given point in 3D space (given as {@link Vector3d})
   * See <a href="https://en.wikipedia.org/wiki/UV_mapping">the Wikipedia article on UV mapping</a>.
   * @param vector the vector to which the texture point is mapped
   * @return a point on the texture image somewhere in the rectangle between (0, 0) and (1, 1)
   */
  public static Vector2d getTextureCoordinate(final Vector3d vector) {
    final double u = 0.5 + (Math.atan2(vector.x, vector.z) / (2 * Math.PI));
    final double v = 0.5 + (Math.asin(vector.y) / Math.PI);
    return new Vector2d(u, v);
  }

  /**
   * For a given point of the texture (i.e. the image), return the point in 3D space where the point
   * of the texture is mapped to (as {@link Vector3d}).
   *
   * @param u x-coordinate of the point on the texture (in the range between 0 and 1, from left to right)
   * @param v y-coordinate of the point on the texture (in the range between 0 and 1, from top to bottom)
   * @return the vector from the origin to where the point of the texture is mapped on the sphere
   */
  public static Vector3d getVector(final double u, final double v) {
    final double vectorY = Math.cos(v * Math.PI);
    return new Vector3d(
        -Math.sin(2 * Math.PI * u) * Math.sqrt(1 - vectorY * vectorY),
        -vectorY,
        -Math.cos(2 * Math.PI * u) * Math.sqrt(1 - vectorY * vectorY)
    );
  }
}
